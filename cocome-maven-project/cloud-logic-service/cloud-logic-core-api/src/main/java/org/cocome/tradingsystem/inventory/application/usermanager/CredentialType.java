package org.cocome.tradingsystem.inventory.application.usermanager;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Enum with available credential types.
 * 
 * @author Tobias Pöppke
 * @author Robert Heinrich
 */
@XmlType(name = "CredentialType", namespace="http://usermanager.application.inventory.tradingsystem.cocome.org/")
@XmlEnum
public enum CredentialType {
	@XmlEnumValue("PASSWORD")
	PASSWORD,
	@XmlEnumValue("AUTH_TOKEN")
	AUTH_TOKEN;
	
	public static final int SIZE = CredentialType.values().length;
}
