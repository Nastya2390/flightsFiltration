package com.gridline.testing.rules;

import com.gridline.testing.Flight;
import com.gridline.testing.Segment;

import java.util.List;

/**
 * Базовый обработчик любого правила
 */
public interface FiltrationRule {
    List<Flight> filter();
}
