import breeze.plot._

object Binary extends App {

    def main(): Unit = {
        val xs = (0 to 1024).toList    // calculate x values (0...1024)
        val ys = for (num <- xs) yield weight(toBinary(num, 8));

        // Now create plot:
        val fig = Figure()
        val plt = fig.subplot(0)
        //plt += plot(xs,ys)
        fig.refresh()
    }

    def toBinary(x: Int, bits: Int): String = {
        if (x > 0) {
            return toBinary(x / 2, bits - 1) + (x % 2).toString;
        }
        else {
            return "0" * bits;
        }
    }

    def weight(b: String): Int = {
        var total: Int = 0;
        b.foreach(bit => total += bit.asDigit);
        return total;
    }
}