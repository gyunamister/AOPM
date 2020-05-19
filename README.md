(*Last updated on 15. May. 2020)*

## Overview

Process mining has provided techniques to extract process-oriented knowledge from event data available in information systems. They have been successfully adopted to solve process-related problems in a diverse domains including healthcare, manufacturing, logistics, etc. Nowadays, the attention of the process mining discipline has shifted to the support of continuous process management and the actual process improvement.

This ProM plugin implements a general framework for action-oriented process mining that supports the continuous management of operational processes and the automated execution of actions to improve the process.

## Tool Description

#### Input

- an event stream
  - An event stream is in Parquet columnar format where columns represent event identifier, process identifier, activity name, resource name, timestamp, object classes, and attribute names.
- constraint formula definitions (CFD)
  - The constraint formulas are defined by *Constraint Formula Definition Language (CFDL)*. The syntax is explained below.
- action formula definitions (AFD)
  - The action formulas are defined by *Action Formula Definition Language (AFDL)*.

#### Output

- action instance stream
  - An action instance stream is in JSON format.

### How to

- Configure filepaths to an event stream, CFD, and AFD.
- Create constraints by selecting constraint formula and specifying when to monitor it.
- Create actions by selecting action formula and specifying when to monitor it.
- Run it.

## Supported constraint formulas and action formulas

The list keeps updated.

## Constraint Formula Definition Language

We define constraint formulas with the constraint formula definition language. The simplified syntax if as follows:

```
DEFINE <<contraint formula name>> AS
CONTEXT <<context description>>
FILTER <<filter description>>
EVALUATE <<predicate description>>
```

#### Example

```
DEFINE "An order must be delivered in 72"  AS
CONTEXT {"Proc":{"OH"}, "omap":{Order:{"foreach"}}}
FILTER "event"
EVALUATE "Throughput", <, 72;
```

Note that  the filter "event" and "Throughput" are pre-defined in the implementation.

## Action Formula Definition Language

We define action formulas with the action formula definition language. The simplified syntax if as follows:

```
DEFINE <<action formula name>> AS
ANALYZE <<analysis description>>
ASSESS <<predicate description>>
OPERATION <<operation name>>
PARAMTER <<parameter mapping description>>
```

#### Example

```
DEFINE "Set higher priority for each delayed order"  AS
ANALYZE {"cf":{"An order must be delivered in 72"}, "Order":{"foreach"}}
ASSESS "count", >, 0;
OPERATION "Set-higher-priority"
PARAMETER "target"="Order", "value"=10;
```

## Experimental environment

To facilitate the test of the framework, we provide an simulation environment with an artificial information system. The information system supports an order handling process described below:



There are 16 available resources in total at any time and each of them is responsible for multiple activities in the process. Orders are randomly placed, and queued for the resource allocation after each activity. The resource is allocated according to *First-in First-out* rule.

### How-to

1. In ProM, you can find the artificial information system with the plug-in name, *artificial information system*. By initializing it, you can generate an *ProcessSimulation* object.
2. With that being the input, you can initiate the AOPM plug-in. You don't have to configure a filepath for an event stream.
3. Run AOPM plug-in.
4. The gateway implemented in the information system automatically translates the action instance stream into transactions and updates the information system.

## Remark