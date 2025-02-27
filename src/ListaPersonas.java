import java.util.ArrayList;
import java.util.List;

public class ListaPersonas {

    private List<Persona> lista = new ArrayList<Persona>();
    public ListaPersonas() {};
    public void add(Persona person){
        lista.add(person);
    }
    public List<Persona> getListaPersonas() {
        return lista;
    }
}
