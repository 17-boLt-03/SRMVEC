% -------- Water Jug Problem (4L & 3L Jugs) --------
:- dynamic rstate/2.
goal(2,_).  % target state
state(X,Y) :-
    goal(X,Y), !,
    format("\nReached goal: (~w,~w)\n", [X,Y]).
state(X,Y) :-
    \+ rstate(X,Y),                
    assert(rstate(X,Y)),
    format("\nState: (~w,~w)", [X,Y]),
    move(X,Y,NX,NY),               
    state(NX,NY).                  
move(X,Y,4,Y) :- X < 4.                      
move(X,Y,X,3) :- Y < 3.                      
move(X,Y,0,Y) :- X > 0.                      
move(X,Y,X,0) :- Y > 0.                      
move(X,Y,NX,NY) :-                           
    X < 4, Y > 0,
    T is min(4-X, Y),
    NX is X + T, NY is Y - T.
move(X,Y,NX,NY) :-                           
    Y < 3, X > 0,
    T is min(3-Y, X),
    NX is X - T, NY is Y + T.
% -------- Goal --------
?- retractall(rstate(_,_)), state(0,0).
