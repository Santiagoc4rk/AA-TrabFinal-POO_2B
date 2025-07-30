package ModeloFactura;

import ModeloPlanCelular.PlanCelular;

public class Factura {
    
    private int numFactura;
    private String cliente;  // Puede ser el nombre o la cédula del cliente
    private PlanCelular plan;
    private String fechaEmision;
    private double totalAPagar;

    // Constructor
    public Factura(int numFactura, String cliente, PlanCelular plan, String fechaEmision) {
        this.numFactura = numFactura;
        this.cliente = cliente;
        this.plan = plan;
        this.fechaEmision = fechaEmision;
        calcularTotal(); // Calculamos el total automáticamente al crear la factura
    }

    // Getters y Setters
    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public PlanCelular getPlan() {
        return plan;
    }

    public void setPlan(PlanCelular plan) {
        this.plan = plan;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(double totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    // Calcula el total a pagar usando el pago mensual del plan
    public void calcularTotal() {
        if (plan != null) {
            plan.calcularPagoMensual();  // Asegura que esté actualizado
            this.totalAPagar = plan.getPagoMensual();
        }
    }
}
