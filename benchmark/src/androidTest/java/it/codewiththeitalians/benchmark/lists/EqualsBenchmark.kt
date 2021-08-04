package it.codewiththeitalians.benchmark.lists

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import it.codewiththeitalians.benchmark.support.BlackHole
import kotlinx.collections.immutable.toImmutableList
import org.junit.Rule
import org.junit.Test
import org.pcollections.TreePVector

class EqualsBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    private val bh = BlackHole()

    @Test
    fun equalSizeKotlinList() {
        equalKotlinList(LIST_SIZE, LIST_SIZE)
    }

    @Test
    fun equalSizeTreePVector() {
        equalTreePVector(LIST_SIZE, LIST_SIZE)
    }

    @Test
    fun equalSizeKotlinImmutables() {
        equalKotlinImmutable(LIST_SIZE, LIST_SIZE)
    }

    @Test
    fun smallerSizeKotlinList() {
        equalKotlinList(LIST_SIZE, LIST_SIZE +1)
    }

    @Test
    fun smallerSizeTreePVector() {
        equalTreePVector(LIST_SIZE, LIST_SIZE + 1)
    }

    @Test
    fun smallerSizeTreePVectorSizeCheck() {
        equalTreePVectorSizeCheck(LIST_SIZE, LIST_SIZE + 1)
    }

    @Test
    fun smallerSizeKotlinImmutables() {
        equalKotlinImmutable(LIST_SIZE, LIST_SIZE + 1)
    }

    @Test
    fun biggerSizeKotlinList() {
        equalKotlinList(LIST_SIZE + 1, LIST_SIZE)
    }

    @Test
    fun biggerSizeTreePVector() {
        equalTreePVector(LIST_SIZE + 1, LIST_SIZE)
    }

    @Test
    fun biggerSizeTreePVectorSizeCheck() {
        equalTreePVectorSizeCheck(LIST_SIZE + 1, LIST_SIZE)
    }

    @Test
    fun biggerSizeKotlinImmutables() {
        equalKotlinImmutable(LIST_SIZE + 1, LIST_SIZE)
    }

    private fun equalKotlinList(size1: Int,  size2: Int) {
        val a = List(size1) { it }
        val b = List(size2) { it }
        benchmarkRule.measureRepeated {
            bh.consume(a == b)
        }
    }

    private fun equalTreePVector(size1: Int,  size2: Int) {
        val a = TreePVector.from(List(size1) { it })
        val b = TreePVector.from(List(size2) { it })
        benchmarkRule.measureRepeated {
            bh.consume(a == b)
        }
    }

    private fun equalTreePVectorSizeCheck(size1: Int,  size2: Int) {
        val a = TreePVector.from(List(size1) { it })
        val b = TreePVector.from(List(size2) { it })
        benchmarkRule.measureRepeated {
            bh.consume(a.size == b.size && a == b)
        }
    }

    private fun equalKotlinImmutable(size1: Int,  size2: Int) {
        val a = List(size1) { it }.toImmutableList()
        val b = List(size2) { it }.toImmutableList()
        benchmarkRule.measureRepeated {
            bh.consume(a == b)
        }
    }

    companion object {
        const val LIST_SIZE: Int = 1_000
    }
}
