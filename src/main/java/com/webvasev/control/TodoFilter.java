package com.webvasev.control;

import com.webvasev.entity.Todo;

@FunctionalInterface
public interface TodoFilter {
    boolean myFilter(Todo todo);
}