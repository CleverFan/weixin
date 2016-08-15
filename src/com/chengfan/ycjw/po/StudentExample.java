package com.chengfan.ycjw.po;

import java.util.ArrayList;
import java.util.List;

public class StudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StudentExample() {
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

        public Criteria andUserweinxinnameIsNull() {
            addCriterion("userWeinxinName is null");
            return (Criteria) this;
        }

        public Criteria andUserweinxinnameIsNotNull() {
            addCriterion("userWeinxinName is not null");
            return (Criteria) this;
        }

        public Criteria andUserweinxinnameEqualTo(String value) {
            addCriterion("userWeinxinName =", value, "userweinxinname");
            return (Criteria) this;
        }

        public Criteria andUserweinxinnameNotEqualTo(String value) {
            addCriterion("userWeinxinName <>", value, "userweinxinname");
            return (Criteria) this;
        }

        public Criteria andUserweinxinnameGreaterThan(String value) {
            addCriterion("userWeinxinName >", value, "userweinxinname");
            return (Criteria) this;
        }

        public Criteria andUserweinxinnameGreaterThanOrEqualTo(String value) {
            addCriterion("userWeinxinName >=", value, "userweinxinname");
            return (Criteria) this;
        }

        public Criteria andUserweinxinnameLessThan(String value) {
            addCriterion("userWeinxinName <", value, "userweinxinname");
            return (Criteria) this;
        }

        public Criteria andUserweinxinnameLessThanOrEqualTo(String value) {
            addCriterion("userWeinxinName <=", value, "userweinxinname");
            return (Criteria) this;
        }

        public Criteria andUserweinxinnameLike(String value) {
            addCriterion("userWeinxinName like", value, "userweinxinname");
            return (Criteria) this;
        }

        public Criteria andUserweinxinnameNotLike(String value) {
            addCriterion("userWeinxinName not like", value, "userweinxinname");
            return (Criteria) this;
        }

        public Criteria andUserweinxinnameIn(List<String> values) {
            addCriterion("userWeinxinName in", values, "userweinxinname");
            return (Criteria) this;
        }

        public Criteria andUserweinxinnameNotIn(List<String> values) {
            addCriterion("userWeinxinName not in", values, "userweinxinname");
            return (Criteria) this;
        }

        public Criteria andUserweinxinnameBetween(String value1, String value2) {
            addCriterion("userWeinxinName between", value1, value2, "userweinxinname");
            return (Criteria) this;
        }

        public Criteria andUserweinxinnameNotBetween(String value1, String value2) {
            addCriterion("userWeinxinName not between", value1, value2, "userweinxinname");
            return (Criteria) this;
        }

        public Criteria andStudentidIsNull() {
            addCriterion("studentID is null");
            return (Criteria) this;
        }

        public Criteria andStudentidIsNotNull() {
            addCriterion("studentID is not null");
            return (Criteria) this;
        }

        public Criteria andStudentidEqualTo(String value) {
            addCriterion("studentID =", value, "studentid");
            return (Criteria) this;
        }

        public Criteria andStudentidNotEqualTo(String value) {
            addCriterion("studentID <>", value, "studentid");
            return (Criteria) this;
        }

        public Criteria andStudentidGreaterThan(String value) {
            addCriterion("studentID >", value, "studentid");
            return (Criteria) this;
        }

        public Criteria andStudentidGreaterThanOrEqualTo(String value) {
            addCriterion("studentID >=", value, "studentid");
            return (Criteria) this;
        }

        public Criteria andStudentidLessThan(String value) {
            addCriterion("studentID <", value, "studentid");
            return (Criteria) this;
        }

        public Criteria andStudentidLessThanOrEqualTo(String value) {
            addCriterion("studentID <=", value, "studentid");
            return (Criteria) this;
        }

        public Criteria andStudentidLike(String value) {
            addCriterion("studentID like", value, "studentid");
            return (Criteria) this;
        }

        public Criteria andStudentidNotLike(String value) {
            addCriterion("studentID not like", value, "studentid");
            return (Criteria) this;
        }

        public Criteria andStudentidIn(List<String> values) {
            addCriterion("studentID in", values, "studentid");
            return (Criteria) this;
        }

        public Criteria andStudentidNotIn(List<String> values) {
            addCriterion("studentID not in", values, "studentid");
            return (Criteria) this;
        }

        public Criteria andStudentidBetween(String value1, String value2) {
            addCriterion("studentID between", value1, value2, "studentid");
            return (Criteria) this;
        }

        public Criteria andStudentidNotBetween(String value1, String value2) {
            addCriterion("studentID not between", value1, value2, "studentid");
            return (Criteria) this;
        }

        public Criteria andYcpasswordIsNull() {
            addCriterion("ycPassword is null");
            return (Criteria) this;
        }

        public Criteria andYcpasswordIsNotNull() {
            addCriterion("ycPassword is not null");
            return (Criteria) this;
        }

        public Criteria andYcpasswordEqualTo(String value) {
            addCriterion("ycPassword =", value, "ycpassword");
            return (Criteria) this;
        }

        public Criteria andYcpasswordNotEqualTo(String value) {
            addCriterion("ycPassword <>", value, "ycpassword");
            return (Criteria) this;
        }

        public Criteria andYcpasswordGreaterThan(String value) {
            addCriterion("ycPassword >", value, "ycpassword");
            return (Criteria) this;
        }

        public Criteria andYcpasswordGreaterThanOrEqualTo(String value) {
            addCriterion("ycPassword >=", value, "ycpassword");
            return (Criteria) this;
        }

        public Criteria andYcpasswordLessThan(String value) {
            addCriterion("ycPassword <", value, "ycpassword");
            return (Criteria) this;
        }

        public Criteria andYcpasswordLessThanOrEqualTo(String value) {
            addCriterion("ycPassword <=", value, "ycpassword");
            return (Criteria) this;
        }

        public Criteria andYcpasswordLike(String value) {
            addCriterion("ycPassword like", value, "ycpassword");
            return (Criteria) this;
        }

        public Criteria andYcpasswordNotLike(String value) {
            addCriterion("ycPassword not like", value, "ycpassword");
            return (Criteria) this;
        }

        public Criteria andYcpasswordIn(List<String> values) {
            addCriterion("ycPassword in", values, "ycpassword");
            return (Criteria) this;
        }

        public Criteria andYcpasswordNotIn(List<String> values) {
            addCriterion("ycPassword not in", values, "ycpassword");
            return (Criteria) this;
        }

        public Criteria andYcpasswordBetween(String value1, String value2) {
            addCriterion("ycPassword between", value1, value2, "ycpassword");
            return (Criteria) this;
        }

        public Criteria andYcpasswordNotBetween(String value1, String value2) {
            addCriterion("ycPassword not between", value1, value2, "ycpassword");
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