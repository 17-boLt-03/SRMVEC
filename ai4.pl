% -------- Travelling Salesman Problem --------

road("tampa","houston",200).
road("gordon","tampa",300).
road("houston","gordon",100).
road("houston","kansas_city",120).
road("gordon","kansas_city",130).

route(T1, T2, D) :-
    road(T1, T2, D).

route(T1, T2, D) :-
    road(T1, X, D1),
    route(X, T2, D2),
    D is D1 + D2.

% -------- Goal --------
% Type this in Prolog:
% ?- route("tampa", "kansas_city", D).
