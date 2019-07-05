package com.celfocus.training.domain;

import java.util.List;

public interface AbstractDao<T> {

	List<T> getAll();

	T create(T entity);

	void delete(String name);
}
