package com.rts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class tabulation implements Serializable {

    private static final long serialVersionUID = 1L;

    private String head;

    private Integer col;

    private String text;

    private Integer row;
}
