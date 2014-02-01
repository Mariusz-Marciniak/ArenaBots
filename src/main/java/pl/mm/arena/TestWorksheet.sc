package pl.mm.arena

object TestWorksheet {

  val producer = new FacingLeftVisibleAreaProducer//> producer  : pl.mm.arena.FacingLeftVisibleAreaProducer = FacingLeftVisibleArea
                                                  //| Producer()
 
      val arena =
   """1oooWoooo1oooWoooo
2abcdefghijklmnopq
3WWWWWWWW3WWWWWWWW
4oooooooo4oooooooo
5W>oWo<Wo5W>oWo<Wo
6oooooooo6oooooooo
7oooooooo7oooooooo
8oooooooo8oooooooo
9oooooooo9oooooooo
WWWWWWWWWWWWWWWWWW
oooo<oooooooo^oooo
0oooWoooo0oooWoooo"""                             //> arena  : String = 1oooWoooo1oooWoooo
                                                  //| 2abcdefghijklmnopq
                                                  //| 3WWWWWWWW3WWWWWWWW
                                                  //| 4oooooooo4oooooooo
                                                  //| 5W>oWo<Wo5W>oWo<Wo
                                                  //| 6oooooooo6oooooooo
                                                  //| 7oooooooo7oooooooo
                                                  //| 8oooooooo8oooooooo
                                                  //| 9oooooooo9oooooooo
                                                  //| WWWWWWWWWWWWWWWWWW
                                                  //| oooo<oooooooo^oooo
                                                  //| 0oooWoooo0oooWoooo
  //producer.validate(arena)
  producer.prepare(arena,(9,9))                   //> res0: String = coooWoooo
                                                  //| dWW<Woooo
                                                  //| eoooWoooo
                                                  //| foooWoooo
                                                  //| goooWoooo
                                                  //| hoooWoooo
                                                  //| i10oW9876
                                                  //| joooWoooo
                                                  //| koooWoooo
  producer.prepare(arena,(5,0))                   //> res1: String = WooooooWl
                                                  //| WooooWoWm
                                                  //| WooooooWn
                                                  //| Woooo<oWo
                                                  //| WooooWoWp
                                                  //| WooooooWq
                                                  //| W98765432
                                                  //| WooooWoWa
                                                  //| Woooo>oWb
  producer.prepare(arena,(1,0))                   //> res2: String = oooWloooW
                                                  //| oWoWmWW^W
                                                  //| oooWnoooW
                                                  //| o<oWooooW
                                                  //| oWoWpoooW
                                                  //| oooWqoooW
                                                  //| 6543210oW
                                                  //| oWoWaoooW
                                                  //| o>oWboooW
  producer.prepare(arena,(0,0))                   //> res3: String = ooWloooWo
                                                  //| WoWmWW^Wo
                                                  //| ooWnoooWo
                                                  //| <oWooooWo
                                                  //| WoWpoooWo
                                                  //| ooWqoooWo
                                                  //| 543210oW9
                                                  //| WoWaoooWo
                                                  //| >oWboooWo
}