import michalz.rpg.traveller.generators.utils.*
import michalz.rpg.traveller.generators.base.table.{TableGen, TableRow}
import michalz.rpg.traveller.generators.base.Dice





val simpleTableGen = TableGen(Dice.d(6), TableRow(1, 3, "Jeden-Trzy"), TableRow(4,5, "Cztery-Pięć"), TableRow(6, "Sześć"))

val results = (1 to 10).map(_ => simpleTableGen.sample).toList.flatten


histogram(groupResults(results)).foreach(println)
