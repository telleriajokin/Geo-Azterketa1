
package paagbi.model.klaseak;

public class Hiria {

    private String izena;
    private Koordenatuak koordenatuak;

    public Hiria(String izena, Koordenatuak koordenatuak) {
        this.izena = izena;
        this.koordenatuak = koordenatuak;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public Koordenatuak getKoordenatuak() {
        return koordenatuak;
    }

    public void setKoordenatuak(Koordenatuak koordenatuak) {
        this.koordenatuak = koordenatuak;
    }
}
