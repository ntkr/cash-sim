# CashSim Design

CashSim is an application which allows cashflows to be visualized.

## Example Use

Eventually the system will handle plain text descriptions of the accounts and transfers:

```
Account AllySavings has $1300 on 1/1/2020.
Account BOAChecking has $500 on 1/1/2020.
TacoBell deposits $100 to AllySavings every 2 weeks beginning 1/20/2020.
AllySavings transfers $150 to BOAChecking every month beginning 2/1/2020.
BOAChecking reserves $10 for Haircuts every month beginning 2/1/2020.
```

## Code Example

The initial implementation will use functions as interfaces:

```
ally = Account('AllySavings', 3000)
boa = Account('BOAChecking', 60)
tacobell = Account('TacoBell')

events = [
    interest(acct: ally, rate: 0.025, compounding: 'monthly', sampling: monthly')
    transfer(amount: 100, from: tacobell, to: ally, every: '14 days', beginning: '1/20/2020'),
    transfer(amount: 150, from: ally, to: boa, every: '1 month', beginning: '1/20/2020'),
    reserve(amount: 10, in: boa, for: 'haircuts', every: '1 month', beginning: '2/1/2020')
]

simulate(accounts, events, end)

```