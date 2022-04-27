def nestedSets(sets):
    lst = []
    for i in sets:                                # Saya tidak tahu cara mengambil SETIAP elemen selain daripada cara ini
        if isinstance(i, (list, tuple, set)):
            lst.extend(nestedSets(i))
        else:
            lst.append(i)
    return lst

def joinString(obj):
    if len(obj) == 0:
        return "Kosong"
    return " ".join(nestedSets(obj))

# sets = eval("input()")
sets = (((('Banana',((((),((((()))))))))),()),(((((((('Ice'))),))),((),('Cream')))))
print(joinString(sets))
