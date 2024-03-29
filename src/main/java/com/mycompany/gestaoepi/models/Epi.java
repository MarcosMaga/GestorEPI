/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.models;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Marcos
 */
public class Epi extends ModelBase{

    private String ca;
    private String marca;
    private int vidaUtil;
    private int quant;
    private Date dataValidade;

    public Epi(String _id, String _nome, String _ca, String _marca, int _vidaUtil, int _quant, Date _dataValidade) {
        super(_id, _nome);
        this.ca = _ca;
        this.marca = _marca;
        this.vidaUtil = _vidaUtil;
        this.quant = _quant;
        this.dataValidade = _dataValidade;
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
        return this.getNome() + " | Data Vencimento: " + this.dataValidade.toString() + " | Vida Util: " + this.vidaUtil + " | Quant: " + this.quant;
    }

    public boolean isValid(LocalDate entrega, int quantidade) {
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

        return true;
    }
}
