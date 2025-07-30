package ModeloPlanCelular;

public class PlanPostPagoMinutosMegas extends PlanCelular {
    
    private int minutosExceso;
    private double megasExceso;
    private double costoMinuto;
    private double costoMega;
    private double costoImpuesto;

    public int getMinutosExceso() {
        return minutosExceso;
    }

    public void setMinutosExceso(int minutosExceso) {
        this.minutosExceso = minutosExceso;
    }

    public double getMegasExceso() {
        return megasExceso;
    }

    public void setMegasExceso(double megasExceso) {
        this.megasExceso = megasExceso;
    }

    public double getCostoMinuto() {
        return costoMinuto;
    }

    public void setCostoMinuto(double costoMinuto) {
        this.costoMinuto = costoMinuto;
    }

    public double getCostoMega() {
        return costoMega;
    }

    public void setCostoMega(double costoMega) {
        this.costoMega = costoMega;
    }

    public double getCostoImpuesto() {
        return costoImpuesto;
    }

    public void setCostoImpuesto(double costoImpuesto) {
        this.costoImpuesto = costoImpuesto;
    }

    @Override
    public void calcularPagoMensual() {
        
        // Costo base puede ser la suma de costoMinuto y costoMega por sus cantidades
        double costoMinutos = minutosExceso * costoMinuto;
        double costoDatos = megasExceso * costoMega;

        double subtotal = costoMinutos + costoDatos;

        // Impuesto sobre subtotal
        double impuesto = subtotal * (costoImpuesto / 100.0);

        this.pagoMensual = subtotal + impuesto;
        
    }
    
}
