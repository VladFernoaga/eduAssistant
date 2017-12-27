package ro.unitbv.eduassistant.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.json.simple.JSONObject;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@MappedSuperclass
public class JsonbValue {

	 /** The raw values. */
	  @Type(type = "jsonb")
	  @Column(name = "jsonb_col", columnDefinition = "jsonb")
	  protected JSONObject jsonbColum;
}
