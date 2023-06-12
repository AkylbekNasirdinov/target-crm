import _ from 'lodash'

class StepParam {
  constructor (props) {
    this.initialize(props)
  }

  initialize (props) {
    /** @type {Number} */ this.id = _.get(props, 'id') || null
    /** @type {String} */ this.value = _.get(props, 'value') || null
    /** @type {String} */ this.parameterType = _.get(props, 'parameterType') || null
    /** @type {Number} */ this.stepId = _.get(props, 'stepId') || null
  }
}

export default StepParam
