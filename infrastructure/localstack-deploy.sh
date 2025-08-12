#!/bin/bash

set -e

aws --endpoint-url=http://localhost:4566 cloudformation deploy \
    --stack-name greenhouse-management \
    --template-file "./cdk.out/LocalStack.template.json"

aws --endpoint-url=http://localhost:4566 elbv2 describe-load-balancers \
    --query "LoadBalancers[0].DNSName" --output text