/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcos
 */
public class Epi {

    private String id;
    private String nome;
    private String ca;
    private String marca;
    private int vidaUtil;
    private int quant;
    private Date dataValidade;

    public Epi(String _id, String _nome, String _ca, String _marca, int _vidaUtil, int _quant, Date _dataValidade) {
        this.id = _id;
        this.nome = _nome;
        this.ca = _ca;
        this.marca = _marca;
        this.vidaUtil = _vidaUtil;
        this.quant = _quant;
        this.dataValidade = _dataValidade;
    }

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCa() {
        return this.ca;
    }

    public String getMarca() {
        return this.marca;
    }

    public int getVidaUtil() {
        return this.vidaUtil;
    }

    public int getQuant() {
        return this.quant;
    }

    public Date getDataValidade() {
        return this.dataValidade;
    }

    public void setId(String _id) {
        this.id = _id;
    }

    public void setNome(String _nome) {
        this.nome = _nome;
    }

    public void setCa(String _ca) {
        this.ca = _ca;
    }

    public void setMarca(String _marca) {
        this.marca = _marca;
    }

    public void setVidaUtil(int _vidaUtil) {
        this.vidaUtil = _vidaUtil;
    }

    public void setQuant(int _quant) {
        this.quant = _quant;
    }

    public void setDataValidade(Date _dataValidade) {
        this.dataValidade = _dataValidade;
    }
    
    public int dataToVenc(){
       Date today = new Date();
       
       long data1 = this.dataValidade.getTime();
       long data2 = today.getTime();
       long timeDiff = Math.abs(data2 - data1);
       long days = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
       return (int)days;
    }

    @Override
    public String toString() {
        return this.nome + " | Data Vencimento: " + this.dataValidade.toString() + " | Vida Util: " + this.vidaUtil + " | Quant: " + this.quant;
    }

    public boolean isValid(LocalDate entrega, int quantidade) {
        System.out.println("Teste maluco");
        Date data = Date.from(entrega.atStartOfDay(ZoneId.systemDefault()).toInstant());
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(data);
            c.add(Calendar.DATE, quantidade * this.vidaUtil);
            data = c.getTime();
            
            if(data.compareTo(this.dataValidade) > 0)
                return false;
          
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        System.out.println("Teste doido");

        System.out.println("Laranja");
        return true;
    }
}
