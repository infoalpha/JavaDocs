import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Date: Apr 13, 2016
 *
 * The source code contained in this listing is proprietary to
 * JPMorgan Chase, Inc.
 *
 * Unauthorized copying, adaptation, distribution,
 * use, or display is strictly prohibited.
 * This software is Copyright 2016 JPMorgan Chase, Inc.
 */

public class Filter {

    public List<Map<String, Object>> getPreferredList(final List<Map<String, Object>> couponList,
            final List<String> preferredCategoryList) {

        System.out.println("coupons list in filter" + couponList.size());
        System.out.println("prefrered list that filter needs to check against : " + preferredCategoryList.size());

        float percentage;

        final List<Map<String, Object>> validList = new ArrayList<Map<String, Object>>();

        final TreeMap<Float, Map<String, Object>> sortedTreeMap = new TreeMap<Float, Map<String, Object>>();

        for (final Map<String, Object> coupon : couponList) {

            for (final String prefCategory : preferredCategoryList) {

                if (prefCategory.equals(coupon.get("category")) && validList.size() < 11) {

                    coupon.remove("code");

                    percentage = percentageCalculator((float) coupon.get("discountPrice"),
                            (float) coupon.get("itemPrice"));

                    sortedTreeMap.put(percentage, coupon);

                }

            }

        }

        System.out.println(sortedTreeMap.descendingKeySet());

        for (final Entry<Float, Map<String, Object>> map : sortedTreeMap.descendingMap().entrySet()) {

            validList.add(map.getValue());

        }

        return validList;

    }

    public float percentageCalculator(final float discountPrice, final float itemPrice) {

        float percentage;

        percentage = (100 * discountPrice) / itemPrice;

        return (percentage);

    }
}
