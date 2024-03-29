package com.salesmanagementsystem.core.model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
public abstract class SalesManagementObject {
    @Id
	@Column(nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@JsonIgnore
	@Column(columnDefinition = "boolean default false")
	private boolean deleted;

	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(nullable = false, updatable = false)
	private Timestamp createdAt;

	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Timestamp updatedAt;

    public String update(SalesManagementObject obj) throws IllegalArgumentException, IllegalAccessException,
			NoSuchMethodException, SecurityException, InvocationTargetException {
		if (obj == null) {
			return "Object in parameter is null";
		}
		if (obj.getClass() != this.getClass()) {
			return "Classes do not match";
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		Method setField;
		for (Field field : fields) {
			field.setAccessible(true);
			if (field.get(obj) != null) {
				String fieldName = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
				setField = obj.getClass().getMethod("set" + fieldName, field.getClass());
				setField.invoke(field.get(obj));
			}
		}
		return null;

	}
}
