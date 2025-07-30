package ModeloPlanCelular;

public class PlanPostPagoMegas extends PlanCelular {
    
    private double megasExceso;
    private double costoExceso;
    private double costoImpuesto;

    public double getMegasExceso() {
        return megasExceso;
    }

    public void setMegasExceso(double megasExceso) {
        this.megasExceso = megasExceso;
    }

    public double getCostoExceso() {
        return costoExceso;
    }

    public void setCostoExceso(double costoExceso) {
        this.costoExceso = costoExceso;
    }

    public double getCostoImpuesto() {
        return costoImpuesto;
    }

    public void setCostoImpuesto(double costoImpuesto) {
        this.costoImpuesto = costoImpuesto;
    }

    @Override
    public void calcularPagoMensual() {
        
        // Costo base es el costoMinutoAdicional (que aquí puede ser el pago fijo base)
        double costoBase = this.costoMinutoAdicional;

        // Costo por megas excedentes
        double costoPorExceso = this.megasExceso * this.costoExceso;

        // Suma subtotal
        double subtotal = costoBase + costoPorExceso;

        // Calculamos impuesto como porcentaje
        double impuesto = subtotal * (this.costoImpuesto / 100.0);

        // Pago total
        this.pagoMensual = subtotal + impuesto;
        
    }
    
}
