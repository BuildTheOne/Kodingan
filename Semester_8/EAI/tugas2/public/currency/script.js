function handleSwitchCurrency() {
  event.preventDefault()

  let currencyCode1 = document.getElementsByName("currencyCode1")[0]
  let currencyCode2 = document.getElementsByName("currencyCode2")[0]
  const temp = currencyCode1.value
  currencyCode1.value = currencyCode2.value
  currencyCode2.value = temp
}