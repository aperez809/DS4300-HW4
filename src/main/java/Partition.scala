object Partition {
    def main() {
        println(moved(100000, 10, 11));
    }

    def moved(records: Int, startN: Int, endN: Int): Double = {
        val old_part = for(i <- 0 to records) yield i % startN;
        val new_part  = for(i <- 0 to records) yield i % endN;
        val num_moved = old_part.zip(new_part).count(pairs => pairs._1 != pairs._2)
        println(num_moved)
        return num_moved.toDouble / records;
    }
}