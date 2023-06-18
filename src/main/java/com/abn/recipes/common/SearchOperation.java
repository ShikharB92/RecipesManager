package com.abn.recipes.common;

public enum SearchOperation {

    CONTAINS, DOES_NOT_CONTAIN, EQUAL, NOT_EQUAL, GREATER_THAN, GREATER_THAN_EQUAL, LESS_THAN, LESS_THAN_EQUAL;

    //cn, nc to be used for string search
    //eq, ne, gt, ge, lt, le to be used for number search
    public static SearchOperation getSimpleOperation(String input) {
        switch (input) {
            case "cn": return CONTAINS;
            case "nc": return DOES_NOT_CONTAIN;
            case "eq": return EQUAL;
            case "ne": return NOT_EQUAL;
            case "gt": return GREATER_THAN;
            case "ge": return GREATER_THAN_EQUAL;
            case "lt": return LESS_THAN;
            case "le": return LESS_THAN_EQUAL;

            default: return null;
        }
    }
}
