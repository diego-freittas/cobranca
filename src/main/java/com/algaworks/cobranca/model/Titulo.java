package com.algaworks.cobranca.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;


@Entity
public class Titulo {
// Criar todos os atributos com os mesmos nomes utilizados nos componentes na viwer	
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long codigo;

private String descricao;

@Temporal(TemporalType.DATE)
private Date dataVencimento;

private BigDecimal valor;

@Enumerated(EnumType.STRING)
private StatusTitulo status;


public Long getCodigo() {
	return codigo;
}
public void setCodigo(Long codigo) {
	this.codigo = codigo;
}
public String getDescricao() {
	return descricao;
}
public void setDescricao(String descricao) {
	this.descricao = descricao;
}
public Date getDataVencimento() {
	return dataVencimento;
}
public void setDataVencimento(Date dataVencimento) {
	this.dataVencimento = dataVencimento;
}
public BigDecimal getValor() {
	return valor;
}
public void setValor(BigDecimal valor) {
	this.valor = valor;
}
public StatusTitulo getStatus() {
	return status;
}
public void setStatus(StatusTitulo status) {
	this.status = status;
}


}