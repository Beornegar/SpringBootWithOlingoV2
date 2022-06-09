package com.dkraus.application.odata.entity.car;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmFacets;
import org.apache.olingo.odata2.api.annotation.edm.EdmKey;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;

import com.dkraus.application.odata.annotation.Sap;

@EdmEntityType
@EdmEntitySet
public class ODataCar {

	@EdmKey
	@EdmProperty
	private long id;

	@EdmProperty
	@Sap(creatable = true)
	private String type;

	@EdmProperty
	private BigDecimal bigDecimal;

	@EdmProperty
	private BigInteger bigInteger;

	@EdmProperty
	private Timestamp timestamp;

	@EdmProperty
	private Long longObject;

	@EdmProperty
	private long longe;

	@EdmProperty
	private Integer intObject;

	@EdmProperty
	private int inte;

	@EdmProperty
	private Boolean boolObject;

	@EdmProperty
	private boolean boolE;

	public ODataCar() {
	}

	public ODataCar(long id, String type, BigDecimal bigDecimal, BigInteger bigInteger, Timestamp timestamp, Long longObject,
			long longe, Integer intObject, int inte, Boolean boolObject, boolean boolE) {
		this.id = id;
		this.type = type;
		this.bigDecimal = bigDecimal;
		this.bigInteger = bigInteger;
		this.timestamp = timestamp;
		this.longObject = longObject;
		this.longe = longe;
		this.intObject = intObject;
		this.inte = inte;
		this.boolObject = boolObject;
		this.boolE = boolE;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getBigDecimal() {
		return this.bigDecimal;
	}

	public void setBigDecimal(BigDecimal bigDecimal) {
		this.bigDecimal = bigDecimal;
	}

	public BigInteger getBigInteger() {
		return this.bigInteger;
	}

	public void setBigInteger(BigInteger bigInteger) {
		this.bigInteger = bigInteger;
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Long getLongObject() {
		return this.longObject;
	}

	public void setLongObject(Long longObject) {
		this.longObject = longObject;
	}

	public long getLonge() {
		return this.longe;
	}

	public void setLonge(long longe) {
		this.longe = longe;
	}

	public Integer getIntObject() {
		return this.intObject;
	}

	public void setIntObject(Integer intObject) {
		this.intObject = intObject;
	}

	public int getInte() {
		return this.inte;
	}

	public void setInte(int inte) {
		this.inte = inte;
	}

	public Boolean isBoolObject() {
		return this.boolObject;
	}

	public Boolean getBoolObject() {
		return this.boolObject;
	}

	public void setBoolObject(Boolean boolObject) {
		this.boolObject = boolObject;
	}

	public boolean isBoolE() {
		return this.boolE;
	}

	public boolean getBoolE() {
		return this.boolE;
	}

	public void setBoolE(boolean boolE) {
		this.boolE = boolE;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof ODataCar)) {
			return false;
		}
		ODataCar car = (ODataCar) o;
		return id == car.id && Objects.equals(type, car.type) && Objects.equals(bigDecimal, car.bigDecimal)
				&& Objects.equals(bigInteger, car.bigInteger) && Objects.equals(longObject, car.longObject)
				&& longe == car.longe && Objects.equals(intObject, car.intObject) && inte == car.inte
				&& Objects.equals(boolObject, car.boolObject) && boolE == car.boolE;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, type, bigDecimal, bigInteger, longObject, longe, intObject, inte, boolObject, boolE);
	}

	@Override
	public String toString() {
		return "{" + " id='" + getId() + "'" + ", type='" + getType() + "'" + ", bigDecimal='" + getBigDecimal() + "'"
				+ ", bigInteger='" + getBigInteger() + "'" + ", timestamp='" + getTimestamp() + "'" + ", longObject='"
				+ getLongObject() + "'" + ", longe='" + getLonge() + "'" + ", intObject='" + getIntObject() + "'"
				+ ", inte='" + getInte() + "'" + ", boolObject='" + isBoolObject() + "'" + ", boolE='" + isBoolE() + "'"
				+ "}";
	}

}
