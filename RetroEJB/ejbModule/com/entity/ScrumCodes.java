package com.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

import org.wildfly.common.annotation.NotNull;

/**
 * Entity implementation class for Entity: ScrumCodes
 *
 */
@Entity
@Table(name="scrumCodes")
@NamedQuery(name="ScrumCodes.findAll", query="SELECT s FROM ScrumCodes s")
public class ScrumCodes implements Serializable {

	   
	@Id
	@NotNull
	@Column(name = "codes")
	private String codes;
	private static final long serialVersionUID = 1L;

	public ScrumCodes() {
		super();
	}
	public String getCodes() {
		return this.codes;
	}
   
}
