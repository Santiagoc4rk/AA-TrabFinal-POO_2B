package ModeloPlanCelular;

public class PlanPostPagoMinutos extends PlanCelular {
    
    private int minutosAdicionales;
    private double costoMinutoNacional;
    private double costoMinutoInternacional;
    private double costoMinutoInterprovincial;

    public int getMinutosAdicionales() {
        return minutosAdicionales;
    }

    public void setMinutosAdicionales(int minutosAdicionales) {
        this.minutosAdicionales = minutosAdicionales;
    }

    public double getCostoMinutoNacional() {
        return costoMinutoNacional;
    }

    public void setCostoMinutoNacional(double costoMinutoNacional) {
        this.costoMinutoNacional = costoMinutoNacional;
    }

    public double getCostoMinutoInternacional() {
        return costoMinutoInternacional;
    }

    public void setCostoMinutoInternacional(double costoMinutoInternacional) {
        this.costoMinutoInternacional = costoMinutoInternacional;
    }

    public double getCostoMinutoInterprovincial() {
        return costoMinutoInterprovincial;
    }

    public void setCostoMinutoInterprovincial(double costoMinutoInterprovincial) {
        this.costoMinutoInterprovincial = costoMinutoInterprovincial;
    }

    @Override
    public void calcularPagoMensual() {
        
         double costoBase = this.costoMinutoAdicional;
    
    // Costo por minutos adicionales por tipo
    // Asumamos que usas minutosAdicionales para minutos consumidos extra (ejemplo)
    double costoNacional = minutosAdicionales * costoMinutoNacional;
    double costoInternacional = minutosAdicionales * costoMinutoInternacional;
    double costoInterprovincial = minutosAdicionales * costoMinutoInterprovincial;

    double costoTotalMinutos = costoNacional + costoInternacional + costoInterprovincial;

    // Pago mensual = base + minutos adicionales (sin impuesto en este ejemplo)
    this.pagoMensual = costoBase + costoTotalMinutos;
       
    }
    
}
