package paagbi.model.klaseak;

import java.util.ArrayList;
import java.util.List;

public class Hiriak {

    private List<Hiria> hiriak;

    public Hiriak() {
        this.hiriak = new ArrayList<>();
    }

    public List<Hiria> getHiriak() {
        return hiriak;
    }

    public void setHiriak(List<Hiria> hiriak) {
        this.hiriak = hiriak;
    }

    public void gehituHiria(Hiria hiria) {
        hiriak.add(hiria);
    }
}
