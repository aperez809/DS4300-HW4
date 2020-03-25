package Redis

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class Redis {
    var stringToStringMap: mutable.HashMap[String, String] = new mutable.HashMap[String, String]();
    var stringToListMap: mutable.HashMap[String, ListBuffer[String]] = new mutable.HashMap[String, ListBuffer[String]]();

    def SSMapDefault(): String = {
        return "";
    }

    def SLMapDefault(): ListBuffer[String] = {
        return ListBuffer[String]();
    }

    // Gets value at `key`, only works on values that are strings
    def get(key: String): Option[String] = {
        return this.stringToStringMap.get(key);
    }

    def getList(key: String): ListBuffer[String] = {
        return this.stringToListMap.getOrElse(key, SLMapDefault())
    }

    // Sets value at `key`, only works on values that are strings
    def set(key: String, value: String): Unit = {
        this.stringToStringMap.put(key, value)
    }

    def lpush(key: String, value: String): Int = {
        val tmp: ListBuffer[String] = this.stringToListMap.getOrElse(key, ListBuffer());
        this.stringToListMap.put(key, tmp.prepend(value));
        return this.stringToListMap.get(key).size;


    }

    def rpush(key: String, value: String): Int = {
        val tmp: ListBuffer[String] = this.stringToListMap.getOrElse(key, SLMapDefault());
        this.stringToListMap.put(key, tmp.append(value));
        return this.stringToListMap.get(key).size

     }

    def lpop(key: String, value: String): String = {
        val tmp: ListBuffer[String] = this.stringToListMap.getOrElse(key, SLMapDefault());
        this.stringToListMap.update(key, tmp.drop(1));
        return tmp.head;
    }

    def rpop(key: String, value: String): String = {
        val tmp: ListBuffer[String] = this.stringToListMap.getOrElse(key, SLMapDefault());
        this.stringToListMap.update(key, tmp.dropRight(1));
        return tmp.last;
    }

    def lrange(key: String, start: Int, stop: Int): ListBuffer[String] = {
        val tmp = this.stringToListMap.getOrElse(key, SLMapDefault());
        if (tmp.isEmpty) {
            return ListBuffer[String]();
        }

        return tmp.slice(start, stop)
    }

    def isValidIndices__(length: Int, start: Int, stop: Int): Boolean = {
        return start <= stop & start <= length & stop <= length - 1;
    }

    def llen(key: String): Int = {
        return this.stringToListMap.getOrElse(key, SLMapDefault()).size;
    }
    def flushall() = {
        this.stringToListMap.clear();
        this.stringToStringMap.clear();
    }
}

