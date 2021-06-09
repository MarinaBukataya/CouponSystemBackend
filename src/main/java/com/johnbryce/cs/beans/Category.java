package com.johnbryce.cs.beans;

import javax.persistence.Table;

@Table(name = "category")
public enum Category {
	FOOD, ELECTRICITY, RESTAURANT, VACATION
}
