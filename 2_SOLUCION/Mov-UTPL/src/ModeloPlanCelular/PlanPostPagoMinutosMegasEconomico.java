package ModeloPlanCelular;

public class PlanPostPagoMinutosMegasEconomico extends PlanCelular{
    
    private int minutos;
    private double costoExceso;
    private double costoGB;
    private double costoImpuesto;
    private double porcentajeDescuento;

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public double getCostoExceso() {
        return costoExceso;
    }

    public void setCostoExceso(double costoExceso) {
        this.costoExceso = costoExceso;
    }

    public double getCostoGB() {
        return costoGB;
    }

    public void setCostoGB(double costoGB) {
        this.costoGB = costoGB;
    }

    public double getCostoImpuesto() {
        return costoImpuesto;
    }

    public void setCostoImpuesto(double costoImpuesto) {
        this.costoImpuesto = costoImpuesto;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public void calcularPagoMensual() {
        
        double costoBase = minutos * costoExceso;
        double costoDatos = costoGB;  // costo fijo sin cantidad
        double subtotal = costoBase + costoDatos;

        double descuento = subtotal * (porcentajeDescuento / 100.0);

        double subtotalConDescuento = subtotal - descuento;

        double impuesto = subtotalConDescuento * (costoImpuesto / 100.0);

        this.pagoMensual = subtotalConDescuento + impuesto;
    }
}