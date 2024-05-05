package com.mstransferenciafinanceira.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Data
public class DPage<T> implements Serializable {

    private Integer codigoRetorno;
    private String mensagem;
    private List<T> conteudo;
    private boolean latsPages;
    private int totalPages;
    private long totalItens;
    private int currentPage;
    private int tolatCurrentItems;

    @JsonIgnore
    public boolean isEmpty(){
        return conteudo == null || conteudo.isEmpty();
    }

    public Iterator<T> interator(){
        return conteudo != null ? conteudo.iterator() : Collections.emptyIterator();
    }

    public void add(T item){
        if (conteudo == null)
            conteudo = new ArrayList<>();
        conteudo.add(item);
    }
}
