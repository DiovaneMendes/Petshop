
package Relatorio;

public class MaisGastam implements Comparable<MaisGastam> {
    
    public MaisGastam(){
        
    }

    @Override
    public int compareTo(MaisGastam ms) {
        if(this.getQuantidade() < mf.getQuantidade()){
            return 1;
        }
        if(this.getQuantidade() > mf.getQuantidade()){
            return -1;
        }
        return 0;     
    }
}
