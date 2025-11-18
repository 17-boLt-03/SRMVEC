solution([]).
solution([c(X,Y)|Others]) :-
    solution(Others),
    member(Y, [1,2,3,4,5,6,7,8]),
    nonattack(c(X,Y), Others).

nonattack(_, []).
nonattack(c(X,Y), [c(X1,Y1)|Others]) :-
    Y =\= Y1,                             % not same row
    abs(Y1 - Y) =\= abs(X1 - X),          % not same diagonal
    nonattack(c(X,Y), Others).

member(X, [X|_]).
member(X, [_|T]) :-
    member(X, T).


