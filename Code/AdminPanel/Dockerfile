FROM node:9

RUN wget https://yarnpkg.com/latest.tar.gz -P /opt
RUN rm -rf /opt/yarn && mkdir /opt/yarn 
RUN cd /opt/ && tar zvxf latest.tar.gz -C /opt/yarn --strip-components 1
RUN yarn --version

RUN rm -rf /tmp/node_modules
ADD package.json yarn.lock /tmp/
RUN cd /tmp && yarn --production

RUN mkdir -p /opt/app && cp -a /tmp/node_modules /opt/app

WORKDIR /opt/app
ADD . /opt/app

CMD ["yarn", "start"]
