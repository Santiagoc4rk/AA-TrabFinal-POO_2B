package ModeloPlanCelular;

import ModeloCliente.Cliente;

public  abstract class PlanCelular {
    
    protected int codigoPlan;
    protected String nombrePlan;
    protected double cupoMinutos;
    protected double costoMinutoAdicional;
    protected Cliente cliente;
    protected double pagoMensual;
    protected String tipoPlan;

    public int getCodigoPlan() {
        return codigoPlan;
    }

    public void setCodigoPlan(int codigoPlan) {
        this.codigoPlan = codigoPlan;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public double getCupoMinutos() {
        return cupoMinutos;
    }

    public void setCupoMinutos(double cupoMinutos) {
        this.cupoMinutos = cupoMinutos;
    }

    public double getCostoMinutoAdicional() {
        return costoMinutoAdicional;
    }

    public void setCostoMinutoAdicional(double costoMinutoAdicional) {
        this.costoMinutoAdicional = costoMinutoAdicional;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getPagoMensual() {
        return pagoMensual;
    }

    public void setPagoMensual(double pagoMensual) {
        this.pagoMensual = pagoMensual;
    }

    public String getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(String tipoPlan) {
        this.tipoPlan = tipoPlan;
    }
    
    public abstract void calcularPagoMensual();
    
    
}
