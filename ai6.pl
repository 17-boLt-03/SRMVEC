go :-
    take("Monkey","Stick"),
    move("Monkey","Chair"),
    get_on("Monkey","Chair"),
    hit("Monkey","Stick","Banana"),
    write("The monkey has hit the banana"), nl.

go :-
    write("The monkey couldn't reach the banana"), nl.


take(Animal,Object) :-
    write("Does the "), write(Animal),
    write(" take the "), write(Object), write(" ? (y/n) "),
    get_char(Reply), get_char(_),     % remove newline
    Reply = 'y'.

move(Animal,Object) :-
    write("Does the "), write(Animal),
    write(" move the "), write(Object), write(" ? (y/n) "),
    get_char(Reply), get_char(_),
    Reply = 'y'.

get_on(Animal,Object) :-
    write("Does the "), write(Animal),
    write(" get on "), write(Object), write(" ? (y/n) "),
    get_char(Reply), get_char(_),
    Reply = 'y'.

hit(Animal,Object,Fruit) :-
    write("Does the "), write(Animal),
    write(" hit the "), write(Fruit),
    write(" with the "), write(Object), write(" ? (y/n) "),
    get_char(Reply), get_char(_),
    Reply = 'y'.
