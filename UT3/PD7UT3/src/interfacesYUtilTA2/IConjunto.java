package interfacesYUtilTA2;

public interface IConjunto<T> extends ILista<T> {
    IConjunto<T> union(IConjunto<T> otro);
    IConjunto<T> interseccion(IConjunto<T> otro);
}
