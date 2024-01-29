package POOExercicio3;


public class Elevador {
    //atributos
   
    int andarAtual;

    //métodos
    //cosntrutor
    public Elevador(int nMaxAndares) {
        this.nMaxAndares = nMaxAndares;
    }
    //gets and sets
    public int getAndarAtual() {
        return andarAtual;
    }
    public void setAndarAtual(int andarAtual) {
        this.andarAtual = andarAtual;
    }
    
    //métodos próprios
    public void inicializa() {
        andarAtual = 0;
        qtdPessoasAtual = 0;
    }
    
    //subir
    public int subir(int nAndares){
        if(andarAtual+nAndares<=nMaxAndares){
            andarAtual+=nAndares;
        } else{
            System.out.println("Digite um nº válido para subir");
        }
        return andarAtual;
    }
    //descer
    public int descer(int nAndares){
        if(andarAtual-nAndares>=0){
            andarAtual-=nAndares;
        } else{
            System.out.println("Digite um nº válido para descer");
        }
        return andarAtual;
    }   
}