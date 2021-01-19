package com.zlsrj.basic.stream.entity;

import java.awt.Color;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
	private int id;
	private String name;
	private int weight;
	private int height;
	private Color color;
}
