package it.codewiththeitalians.benchmark.lists

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import it.codewiththeitalians.benchmark.support.BlackHole
import kotlinx.collections.immutable.toImmutableList
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.pcollections.TreePVector

@RunWith(Parameterized::class)
class IterationBenchmark(private val size: Int) {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    private val bh = BlackHole()

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "size={0}")
        fun data(): Array<Int> {
            return arrayOf(
                1,
                10,
                100,
                1_000,
                10_000,
                100_000,
                1_000_000,
            );
        }
    }

    @Test
    fun kotlinList() {
        val list = List(size) { it }
        benchmarkRule.measureRepeated {
            for (i in list) {
                bh.consume(i)
            }
        }
    }

    @Test
    fun treePVector() {
        val list = TreePVector.from(List(size) { it })
        benchmarkRule.measureRepeated {
            for (i in list) {
                bh.consume(i)
            }
        }
    }

    @Test
    fun kotlinImmutableList() {
        val list = List(size) { it }.toImmutableList()
        benchmarkRule.measureRepeated {
            for (i in list) {
                bh.consume(i)
            }
        }
    }
}