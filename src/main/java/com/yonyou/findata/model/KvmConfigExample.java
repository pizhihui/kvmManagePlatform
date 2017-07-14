package com.yonyou.findata.model;

import java.util.ArrayList;
import java.util.List;

public class KvmConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public KvmConfigExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andConfigKeyIsNull() {
            addCriterion("config_key is null");
            return (Criteria) this;
        }

        public Criteria andConfigKeyIsNotNull() {
            addCriterion("config_key is not null");
            return (Criteria) this;
        }

        public Criteria andConfigKeyEqualTo(String value) {
            addCriterion("config_key =", value, "configKey");
            return (Criteria) this;
        }

        public Criteria andConfigKeyNotEqualTo(String value) {
            addCriterion("config_key <>", value, "configKey");
            return (Criteria) this;
        }

        public Criteria andConfigKeyGreaterThan(String value) {
            addCriterion("config_key >", value, "configKey");
            return (Criteria) this;
        }

        public Criteria andConfigKeyGreaterThanOrEqualTo(String value) {
            addCriterion("config_key >=", value, "configKey");
            return (Criteria) this;
        }

        public Criteria andConfigKeyLessThan(String value) {
            addCriterion("config_key <", value, "configKey");
            return (Criteria) this;
        }

        public Criteria andConfigKeyLessThanOrEqualTo(String value) {
            addCriterion("config_key <=", value, "configKey");
            return (Criteria) this;
        }

        public Criteria andConfigKeyLike(String value) {
            addCriterion("config_key like", value, "configKey");
            return (Criteria) this;
        }

        public Criteria andConfigKeyNotLike(String value) {
            addCriterion("config_key not like", value, "configKey");
            return (Criteria) this;
        }

        public Criteria andConfigKeyIn(List<String> values) {
            addCriterion("config_key in", values, "configKey");
            return (Criteria) this;
        }

        public Criteria andConfigKeyNotIn(List<String> values) {
            addCriterion("config_key not in", values, "configKey");
            return (Criteria) this;
        }

        public Criteria andConfigKeyBetween(String value1, String value2) {
            addCriterion("config_key between", value1, value2, "configKey");
            return (Criteria) this;
        }

        public Criteria andConfigKeyNotBetween(String value1, String value2) {
            addCriterion("config_key not between", value1, value2, "configKey");
            return (Criteria) this;
        }

        public Criteria andConfigValueIsNull() {
            addCriterion("config_value is null");
            return (Criteria) this;
        }

        public Criteria andConfigValueIsNotNull() {
            addCriterion("config_value is not null");
            return (Criteria) this;
        }

        public Criteria andConfigValueEqualTo(String value) {
            addCriterion("config_value =", value, "configValue");
            return (Criteria) this;
        }

        public Criteria andConfigValueNotEqualTo(String value) {
            addCriterion("config_value <>", value, "configValue");
            return (Criteria) this;
        }

        public Criteria andConfigValueGreaterThan(String value) {
            addCriterion("config_value >", value, "configValue");
            return (Criteria) this;
        }

        public Criteria andConfigValueGreaterThanOrEqualTo(String value) {
            addCriterion("config_value >=", value, "configValue");
            return (Criteria) this;
        }

        public Criteria andConfigValueLessThan(String value) {
            addCriterion("config_value <", value, "configValue");
            return (Criteria) this;
        }

        public Criteria andConfigValueLessThanOrEqualTo(String value) {
            addCriterion("config_value <=", value, "configValue");
            return (Criteria) this;
        }

        public Criteria andConfigValueLike(String value) {
            addCriterion("config_value like", value, "configValue");
            return (Criteria) this;
        }

        public Criteria andConfigValueNotLike(String value) {
            addCriterion("config_value not like", value, "configValue");
            return (Criteria) this;
        }

        public Criteria andConfigValueIn(List<String> values) {
            addCriterion("config_value in", values, "configValue");
            return (Criteria) this;
        }

        public Criteria andConfigValueNotIn(List<String> values) {
            addCriterion("config_value not in", values, "configValue");
            return (Criteria) this;
        }

        public Criteria andConfigValueBetween(String value1, String value2) {
            addCriterion("config_value between", value1, value2, "configValue");
            return (Criteria) this;
        }

        public Criteria andConfigValueNotBetween(String value1, String value2) {
            addCriterion("config_value not between", value1, value2, "configValue");
            return (Criteria) this;
        }

        public Criteria andInfoIsNull() {
            addCriterion("info is null");
            return (Criteria) this;
        }

        public Criteria andInfoIsNotNull() {
            addCriterion("info is not null");
            return (Criteria) this;
        }

        public Criteria andInfoEqualTo(String value) {
            addCriterion("info =", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotEqualTo(String value) {
            addCriterion("info <>", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoGreaterThan(String value) {
            addCriterion("info >", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoGreaterThanOrEqualTo(String value) {
            addCriterion("info >=", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLessThan(String value) {
            addCriterion("info <", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLessThanOrEqualTo(String value) {
            addCriterion("info <=", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLike(String value) {
            addCriterion("info like", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotLike(String value) {
            addCriterion("info not like", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoIn(List<String> values) {
            addCriterion("info in", values, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotIn(List<String> values) {
            addCriterion("info not in", values, "info");
            return (Criteria) this;
        }

        public Criteria andInfoBetween(String value1, String value2) {
            addCriterion("info between", value1, value2, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotBetween(String value1, String value2) {
            addCriterion("info not between", value1, value2, "info");
            return (Criteria) this;
        }

        public Criteria andOsTypeIsNull() {
            addCriterion("os_type is null");
            return (Criteria) this;
        }

        public Criteria andOsTypeIsNotNull() {
            addCriterion("os_type is not null");
            return (Criteria) this;
        }

        public Criteria andOsTypeEqualTo(Integer value) {
            addCriterion("os_type =", value, "osType");
            return (Criteria) this;
        }

        public Criteria andOsTypeNotEqualTo(Integer value) {
            addCriterion("os_type <>", value, "osType");
            return (Criteria) this;
        }

        public Criteria andOsTypeGreaterThan(Integer value) {
            addCriterion("os_type >", value, "osType");
            return (Criteria) this;
        }

        public Criteria andOsTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("os_type >=", value, "osType");
            return (Criteria) this;
        }

        public Criteria andOsTypeLessThan(Integer value) {
            addCriterion("os_type <", value, "osType");
            return (Criteria) this;
        }

        public Criteria andOsTypeLessThanOrEqualTo(Integer value) {
            addCriterion("os_type <=", value, "osType");
            return (Criteria) this;
        }

        public Criteria andOsTypeIn(List<Integer> values) {
            addCriterion("os_type in", values, "osType");
            return (Criteria) this;
        }

        public Criteria andOsTypeNotIn(List<Integer> values) {
            addCriterion("os_type not in", values, "osType");
            return (Criteria) this;
        }

        public Criteria andOsTypeBetween(Integer value1, Integer value2) {
            addCriterion("os_type between", value1, value2, "osType");
            return (Criteria) this;
        }

        public Criteria andOsTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("os_type not between", value1, value2, "osType");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}