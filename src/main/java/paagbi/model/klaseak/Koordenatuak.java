
package paagbi.model.klaseak;

public class Koordenatuak {

    private String latitudea;
    private String longitudea;

    public Koordenatuak(String latitudea, String longitudea) {
        this.latitudea = latitudea;
        this.longitudea = longitudea;
    }

    public String getLatitudea() {
        return latitudea;
    }

    public void setLatitudea(String latitudea) {
        this.latitudea = latitudea;
    }

    public String getLongitudea() {
        return longitudea;
    }

    public void setLongitudea(String longitudea) {
        this.longitudea = longitudea;
    }

    public double distantzia(Koordenatuak puntua) {

        double lat1 = Double.parseDouble(this.latitudea);
        double lon1 = Double.parseDouble(this.longitudea);

        double lat2 = Double.parseDouble(puntua.getLatitudea());
        double lon2 = Double.parseDouble(puntua.getLongitudea());

        return Math.pow(lat2 - lat1, 2)
                + Math.pow(lon2 - lon1, 2);
    }
}