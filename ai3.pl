% -------- Depth First Search --------

% Facts
child(a,b).   % b is child of a
child(a,c).   % c is child of a
child(a,d).   % d is child of a
child(b,e).   % e is child of b
child(b,f).   % f is child of b
child(c,g).   % g is child of c

% Rule to find path from A to G
path(A, G, [A|Z]) :-
    childnode(A, G, Z).

% Direct child → path ends
childnode(A, G, [G]) :-
    child(A, G).

% Recursive case
childnode(A, G, [X|L]) :-
    child(A, X),
    childnode(X, G, L).

% -------- Goal --------
?- path(a, g, Path).
