@GenericGenerator(
        name = "id_generator",
        strategy = "org.hibernate.id.enhanced.TableGenerator",
        parameters = {
                @Parameter(name = "table_name", value = "id_generator"),
                @Parameter(name = "segment_column_name", value = "gen_name"),
                @Parameter(name = "value_column_name", value = "gen_val"),
                @Parameter(name = "initial_value", value = "10"),
                @Parameter(name = "increment_size", value = "30"),
                @Parameter(name = "optimizer", value = "pooled-lo"),
                // в gen_name добавить имя таблицы для которой берем id
                @Parameter(name = "prefer_entity_table_as_segment_value", value = "true")
        }
)
package com.rk.ibs.lab2.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;