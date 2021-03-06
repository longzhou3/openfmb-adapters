/**
 * Copyright 2016 Green Energy Corp.
 *
 * Licensed to Green Energy Corp (www.greenenergycorp.com) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. Green Energy
 * Corp licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.greenenergycorp.openfmb.mapping.model;

/**
 * Abstract class for individual data updates.
 *
 * Concrete implementations are boolean, integer (long), double, or string values. All can optionally
 * be converted to the other formats.
 */
public abstract class MeasValue {

    /**
     * Converts value to boolean.
     *
     * @return Boolean value. Null if conversion is not possible.
     */
    public abstract Boolean asBoolean();

    /**
     * Converts value to long.
     *
     * @return Long value. Null if conversion is not possible.
     */
    public abstract Long asLong();

    /**
     * Converts value to double.
     *
     * @return Double value. Null if conversion is not possible.
     */
    public abstract Double asDouble();

    /**
     * Converts value to string.
     *
     * @return String value. Null if conversion is not possible.
     */
    public abstract String asString();

    /**
     * Checks whether two meas values are equivalent.
     *
     * @param v Other meas value.
     * @return True if the values are equivalent.
     */
    public abstract boolean matches(MeasValue v);

    /**
     * Boolean data update.
     */
    public static class MeasBoolValue extends MeasValue {
        private final boolean value;

        public MeasBoolValue(boolean value) {
            this.value = value;
        }

        public boolean getValue() {
            return value;
        }

        @Override
        public Boolean asBoolean() {
            return value;
        }

        @Override
        public Long asLong() {
            return (long)(value ? 1 : 0);
        }

        @Override
        public Double asDouble() {
            return (double)(value ? 1 : 0);
        }

        @Override
        public String asString() {
            return String.valueOf(value);
        }

        @Override
        public boolean matches(MeasValue v) {
            final Boolean other = v.asBoolean();
            return other != null && other == value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MeasBoolValue that = (MeasBoolValue) o;

            return value == that.value;

        }

        @Override
        public int hashCode() {
            return (value ? 1 : 0);
        }

        @Override
        public String toString() {
            return "MeasBoolValue(" + value + ")";
        }
    }


    /**
     * Integer data update.
     */
    public static class MeasIntValue extends MeasValue {
        private final long value;

        public MeasIntValue(long value) {
            this.value = value;
        }

        public long getValue() {
            return value;
        }

        @Override
        public Boolean asBoolean() {
            return value != 0;
        }

        @Override
        public Long asLong() {
            return value;
        }

        @Override
        public Double asDouble() {
            return (double)value;
        }

        @Override
        public String asString() {
            return String.valueOf(value);
        }

        @Override
        public boolean matches(MeasValue v) {
            final Long other = v.asLong();
            return other != null && other == value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MeasIntValue that = (MeasIntValue) o;

            return value == that.value;

        }

        @Override
        public int hashCode() {
            return (int) (value ^ (value >>> 32));
        }

        @Override
        public String toString() {
            return "MeasIntValue(" + value + ")";
        }
    }

    /**
     * Floating-point data update.
     */
    public static class MeasFloatValue extends MeasValue {
        private final double value;

        public MeasFloatValue(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public Boolean asBoolean() {
            return value != 0d;
        }

        @Override
        public Long asLong() {
            return (long)value;
        }

        @Override
        public Double asDouble() {
            return value;
        }

        @Override
        public String asString() {
            return String.valueOf(value);
        }

        @Override
        public boolean matches(MeasValue v) {
            final Double other = v.asDouble();
            return other != null && other == value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MeasFloatValue that = (MeasFloatValue) o;

            return Double.compare(that.value, value) == 0;

        }

        @Override
        public int hashCode() {
            long temp = Double.doubleToLongBits(value);
            return (int) (temp ^ (temp >>> 32));
        }

        @Override
        public String toString() {
            return "MeasFloatValue(" + value + ")";
        }
    }

    /**
     * String data update.
     */
    public static class MeasStringValue extends MeasValue {
        private final String value;

        public MeasStringValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public Boolean asBoolean() {
            return null;
        }

        @Override
        public Long asLong() {
            return null;
        }

        @Override
        public Double asDouble() {
            return null;
        }

        @Override
        public String asString() {
            return value;
        }

        @Override
        public boolean matches(MeasValue v) {
            final String other = v.asString();
            return other != null && other.equals(value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MeasStringValue that = (MeasStringValue) o;

            return !(value != null ? !value.equals(that.value) : that.value != null);

        }

        @Override
        public int hashCode() {
            return value != null ? value.hashCode() : 0;
        }

        @Override
        public String toString() {
            return "MeasStringValue(" + value + ")";
        }
    }

}
