/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcos
 */
public class Entrega {
    private String id;
    private String func;
    private String epi;
    private int vida_util;
    private String data_entrega;
    private String data_devolucao;
    private int quant;
    private boolean confirmado;
    
    public Entrega(String _id, String _func, String _epi, int _vida_util, String _data_entrega, String _data_devolucao, int _quant, boolean _confirm){
        this.id = _id;
        this.func = _func;
        this.epi = _epi;
        this.vida_util = _vida_util;
        this.data_entrega = _data_entrega;
        this.data_devolucao = _data_devolucao;
        this.quant = _quant;
        this.confirmado = _confirm;
    }
    
    public int dataFaltante(){
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        int value = 0;
        try {
            Date dataPrevista = fm.parse(this.data_entrega);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataPrevista);
            cal.add(Calendar.DAY_OF_MONTH, this.vida_util * this.quant);
            dataPrevista = cal.getTime();
            Date hoje = new Date();
            long diff = dataPrevista.getTime() - hoje.getTime();
            value = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException ex) {
            Logger.getLogger(Entrega.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(value < 0)
            return 0;
        
        return value;
    }
    
    public String dataCompleta(){
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        Date dataPrevista = null;
        try {
            dataPrevista = fm.parse(this.data_entrega);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataPrevista);
            cal.add(Calendar.DAY_OF_MONTH, this.vida_util * this.quant);
            dataPrevista = cal.getTime();
        } catch (ParseException ex) {
            Logger.getLogger(Entrega.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return fm.format(dataPrevista).toString();
    }

    public String getId() {
        return id;
    }

    public String getFunc() {
        return func;
    }

    public String getEpi() {
        return epi;
    }

    public int getVida_util() {
        return vida_util;
    }

    public String getData_entrega() {
        return data_entrega;
    }

    public String getData_devolucao() {
        return data_devolucao;
    }

    public int getQuant() {
        return quant;
    }

    public boolean isConfirmado() {
        return confirmado;
    }
    
}
