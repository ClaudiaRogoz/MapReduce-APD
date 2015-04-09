public class Pair<L,R> implements Comparable<Pair<L,R>>{

  private final L left;
  final R sim;

  public Pair(L left, R sim) {
    this.left = left;
    this.sim = sim;
  }

  public L getLeft() { return left; }
  public R getRight() { return sim; }

  @Override
  public int hashCode() { return left.hashCode() ^ sim.hashCode(); }

  @Override
  public boolean equals(Object o) {
    if (o == null) return false;
    if (!(o instanceof Pair)) return false;
    Pair pairo = (Pair) o;
    return this.left.equals(pairo.getLeft()) &&
           this.sim.equals(pairo.getRight());
  }
  
  public String toString(){
	 return left + ";" + sim;
  }

@Override
public int compareTo(Pair<L,R> o) {
	if (((Double)this.sim) > ((Double)o.sim))

        return -1;

 else if (((Double)o.sim) > ((Double)this.sim))

         return 1;

 else

         return 0;
}

}